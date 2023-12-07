package kq.practice.ssf19.model;

import org.springframework.stereotype.Component;

@Component("briefcase")
public class Briefcase extends Bag {

    @Override
    public void showBagType() {
        System.out.println("You are carrying a briefcase");
    }    
}
