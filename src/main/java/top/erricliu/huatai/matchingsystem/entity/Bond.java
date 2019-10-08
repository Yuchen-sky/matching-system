package top.erricliu.huatai.matchingsystem.entity;

import lombok.Data;

/**
 * @author liubi
 * @date 2019-10-08 10:31
 **/
@Data
public class Bond implements Comparable {
    private static int currentid = 0;
    int id;
    int quantity;


    // 总量
    public Bond(int quantity) {
        this.id = currentid++;
        this.quantity = quantity;
    }

    public Bond() {
    }

    @Override
    public int compareTo(Object o) {
        Bond other = (Bond) o;
        if (other.id != this.id) {
            return this.id - other.id;
        } else {
            return this.quantity - other.quantity;
        }
    }
}
