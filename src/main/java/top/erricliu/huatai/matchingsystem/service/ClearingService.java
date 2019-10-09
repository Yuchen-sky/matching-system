package top.erricliu.huatai.matchingsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.User;
import top.erricliu.huatai.matchingsystem.entity.billList.BuyBillList;
import top.erricliu.huatai.matchingsystem.entity.billList.SaleBillList;
import top.erricliu.huatai.matchingsystem.entity.transaction.BuyBill;
import top.erricliu.huatai.matchingsystem.entity.transaction.SaleBill;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;
import top.erricliu.huatai.matchingsystem.repo.BillRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

import java.sql.Timestamp;

/**
 * @author liubi
 * @date 2019-10-08 23:53
 **/
@Service
@Log4j2
public class ClearingService {
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private UserRepo userRepo;

    public synchronized void clearBuy(BuyBill buyBill, SaleBillList saleList, Object[] pricing) {
        // t1 未加入repo ,t2 已经加入repo
        // 成交价格为 先进入系统的价格 Object[0]
        // 成交数量为二者较小值 Object[1]
        // buyBill Object[2]
        // saleBill Object[3]
        SaleBill saleBill = (SaleBill) pricing[3];
        Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        User buyer = userRepo.get(buyBill.getUserId());
        User seller = userRepo.get(saleBill.getUserId());
        // 生成交易
        Transaction transaction = new Transaction(buyer.getId(), seller.getId(), buyBill.getBoundId(), (int)pricing[1], (int)pricing[0]);

        buyBill.trade((int)pricing[1], timestamp);
        saleBill.trade((int)pricing[1], timestamp);
        if (saleBill.getQuantity() == 0) {
            saleList.removeBill(saleBill);
        }
        buyer.buy(buyBill.getBoundId(), (int)pricing[1], (int)pricing[0]);
        seller.sale(saleBill.getBoundId(), (int)pricing[1], (int)pricing[0]);
        //log
        log.info("transaction deal:" + transaction.toJson());
    }

/*    public synchronized void clearSale(SaleBill saleBill, BuyBillList buyList, int[] pricing) {
        // t1 未加入repo ,t2 已经加入repo
        // 成交价格为 先进入系统的价格 int[0]
        // 成交数量为二者较小值 int[1]
        BuyBill buyBill = (BuyBill) buyList.peekBill();
        Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        User buyer = userRepo.get(buyBill.getUserId());
        User seller = userRepo.get(saleBill.getUserId());
        // 生成交易
        Transaction transaction = new Transaction(buyer.getId(), seller.getId(), buyBill.getBoundId(), pricing[1], pricing[0]);

        buyBill.trade(pricing[1], timestamp);
        saleBill.trade(pricing[1], timestamp);
        if (buyBill.getQuantity() == 0) {
            buyList.pollBill();
        }
        buyer.buy(buyBill.getBoundId(), pricing[1], pricing[0]);
        seller.sale(saleBill.getBoundId(), pricing[1], pricing[0]);
        //log
        log.info("transaction deal:" + transaction.toJson());
    }*/

    public synchronized void clearSale(SaleBill saleBill, BuyBillList buyList, Object[] pricing) {
        // t1 未加入repo ,t2 已经加入repo
        // 成交价格为 先进入系统的价格 Object[0]
        // 成交数量为二者较小值 Object[1]
        // buyBill Object[2]
        // saleBill Object[3]
        BuyBill buyBill = (BuyBill) pricing[2];
        Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        User buyer = userRepo.get(buyBill.getUserId());
        User seller = userRepo.get(saleBill.getUserId());
        // 生成交易
        Transaction transaction = new Transaction(buyer.getId(), seller.getId(), buyBill.getBoundId(), (int)pricing[1], (int)pricing[0]);

        if(!clearPreCheck(buyer,seller,transaction)){
            //log.error("error transaction, Transaction:"+transaction+);
            return;
           // return false;
        }
        buyBill.trade((int)pricing[1], timestamp);
        saleBill.trade((int)pricing[1], timestamp);
        if (buyBill.getQuantity() == 0) {
            buyList.removeBill(buyBill);
        }
        buyer.buy(buyBill.getBoundId(), (int)pricing[1], (int)pricing[0]);
        seller.sale(saleBill.getBoundId(), (int)pricing[1], (int)pricing[0]);
        //log
        log.info("transaction deal:" + transaction.toJson());
        return;
        //return true;
    }

    private boolean clearPreCheck(User buyer, User seller,Transaction transaction){
        if (buyer.getFrozenMoney()<transaction.getQuantity()*transaction.getDealingPrice()){
            return false;
        }
        if(seller.getBonds().get(transaction.getBondId()).getFrozenQuantity()<transaction.getQuantity()){
            return false;
        }
        return true;
    }


}
