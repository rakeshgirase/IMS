package com.exuberant.ims.bll;
import com.exuberant.ims.dal.SellCart;
import com.exuberant.ims.getway.SellCartGerway;
public class SellCartBLL {
    SellCartGerway sellCartGerway = new SellCartGerway();

    public void sell(SellCart sellCart) {
        updateCurrentQuentity(sellCart);
        this.sellCartGerway.save(sellCart);
    }
    public void updateCurrentQuentity(SellCart sellCart) {
        System.out.println("In Update");
        int oQ = Integer.parseInt(sellCart.oldQuentity);
        int nQ = Integer.parseInt(sellCart.quantity);
        int uQ = oQ - nQ;
        System.out.println("NOW QUENTITY IS: " + uQ);
        String updatedQuentity = String.valueOf(uQ);
        System.out.println("Update Complate");
    }
}