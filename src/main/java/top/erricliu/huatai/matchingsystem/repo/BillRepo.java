package top.erricliu.huatai.matchingsystem.repo;

import org.springframework.stereotype.Component;
import top.erricliu.huatai.matchingsystem.entity.billList.BuyBillList;
import top.erricliu.huatai.matchingsystem.entity.billList.SaleBillList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liubi
 * @date 2019-10-08 21:48
 **/
@Component
public class BillRepo {
    Map<Integer, BuyBillList> buyRepo = new HashMap<>();
    Map<Integer, SaleBillList> saleRepo = new HashMap<>();

    public void addList(int bondId) {
        buyRepo.put(bondId, new BuyBillList(bondId));
        saleRepo.put(bondId, new SaleBillList(bondId));
    }

    public SaleBillList getSaleList(int bondId) {
        return saleRepo.get(bondId);
    }

    public BuyBillList getBuyList(int bondId) {
        return buyRepo.get(bondId);
    }
}
