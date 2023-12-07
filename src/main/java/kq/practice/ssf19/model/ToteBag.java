package kq.practice.ssf19.model;

import org.springframework.stereotype.Component;

@Component("totebag")
public class ToteBag extends Bag {
   
    @Override
    public void showBagType() {
        System.out.println("You are carrying a totebag");
    }

}
