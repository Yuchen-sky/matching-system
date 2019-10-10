package top.erricliu.huatai.matchingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.erricliu.huatai.matchingsystem.entity.billList.BillList;
import top.erricliu.huatai.matchingsystem.entity.billList.BuyBillList;
import top.erricliu.huatai.matchingsystem.entity.billList.SaleBillList;
import top.erricliu.huatai.matchingsystem.entity.transaction.*;
import top.erricliu.huatai.matchingsystem.repo.BillRepo;
import top.erricliu.huatai.matchingsystem.repo.UserRepo;

import java.util.Queue;

/**
 * @author liubi
 * @date 2019-10-08 23:20
 **/
@Service
public class MatchingService {
    @Autowired
    private BillRepo billRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ClearingService clearingService;

    public void activate(Bill bill) {
        if (bill.getTradeType().equals(BillType.BUY)) {
          activateByBuy((BuyBill) bill);
        } else {
            activateBySale((SaleBill) bill);
        }
    }

    private void activateByBuy(BuyBill buyBill) {
        SaleBillList saleList = billRepo.getSaleList(buyBill.getBoundId());
        BuyBillList buyList = billRepo.getBuyList(buyBill.getBoundId());
        while (buyBill.getQuantity() != 0) {
            Object[] res = pricing(buyBill, saleList);
            if(res==null){
                buyList.offerBill(buyBill);
                return;
            }
            if(!clearingService.clearBuy(buyBill, saleList, res)){
                buyList.offerBill(buyBill);
                return;
            }
        }
    }

    private void activateBySale(SaleBill saleBill) {
        SaleBillList saleList = billRepo.getSaleList(saleBill.getBoundId());
        BuyBillList buyList = billRepo.getBuyList(saleBill.getBoundId());
        while (saleBill.getQuantity() != 0) {
            Object[] res = pricing(saleBill, buyList);
            if(res==null){
                saleList.offerBill(saleBill);
                return;
            }
            if (!clearingService.clearSale(saleBill, buyList, res)){
                saleList.offerBill(saleBill);
                return;
            }
        }
    }

    private Object[] pricing(Bill bill, BillList billList){
        // 成交价格为 先进入系统的价格 Object[0]
        // 成交数量为二者较小值 Object[1]
        // buyBill Object[2]
        // saleBill Object[3]
        boolean isBuy  = bill.getTradeType().equals(BillType.BUY);
        BuyBill buyBill = null;
        SaleBill saleBill = null;
        if (isBuy){
            buyBill = (BuyBill) bill;
        }else {
            saleBill = (SaleBill) bill;
        }
        Queue<Bill> billQueue = billList.getTransList();
        for(Bill tmp: billQueue){
            if (isBuy){
                if(buyBill.getPrice()>=tmp.getPrice()){
                    saleBill = (SaleBill) tmp;
                    break;
                }
            }else {
                if(saleBill.getPrice()<=tmp.getPrice()){
                    buyBill = (BuyBill) tmp;
                    break;
                }
            }
        }
        if(isBuy&&saleBill==null){
            return null;
        }
        if((!isBuy)&&buyBill==null){
            return null;
        }
        if (buyBill.getPrice() < saleBill.getPrice()) {
            // 无法撮合
            return null;
        } else {
            int price;
            int delt = buyBill.getInitTime().compareTo(saleBill.getInitTime());
            if (delt < 0) {
                price = buyBill.getPrice();
            } else if (delt > 0) {
                price = saleBill.getPrice();
            } else {
                delt = buyBill.getCurrentTime().compareTo(saleBill.getCurrentTime());
                if (delt < 0) {
                    price = buyBill.getPrice();
                } else {
                    price = saleBill.getPrice();
                }
            }
            int quantity = Math.min(buyBill.getQuantity(), saleBill.getQuantity());
            return new Object[]{price, quantity,buyBill,saleBill};
        }
    }
}
