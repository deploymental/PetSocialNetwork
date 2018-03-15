package old;

import com.getjavajob.AccountDAO;
import com.getjavajob.common.Account;
import com.getjavajob.common.enums.Sex;

import java.sql.Date;
import java.sql.SQLException;

public class testssss {
    public static void main(String[] args) throws SQLException {
        /*AccountDAO ad = new AccountDAO("DAOProperties");
        List<Account> acs = ad.getAll();
        System.out.println(acs.size());
        if (acs != null && acs.size() > 0) {
            for (com.getjavajob.common.Account ac : acs) {
                if (ac.getName() != null) {
                    System.out.println(ac.getName());
                }
                if (ac.getSurName() != null) {
                    System.out.println(ac.getSurName());
                }
                *//*List<Phone> ph = ac.getPhones();
                if (ph != null && ph.size() > 0) {
                    for (Phone p : ac.getPhones()) {
                        if (p != null) {
                            System.out.println(p);
                        }
                    }
                }*//*
                System.out.println(ac.getHomeAddress());
            }
        }*/
        AccountDAO ad = new AccountDAO();
        Account acc = new Account();
        acc.setName("Vasya");
        acc.setMiddleName("Vasylevich");
        acc.setSurName("Vasyacovich");
        acc.setSex(Sex.valueOf("MALE"));
        acc.setBirthDate(new Date(2134235));
        acc.setHomeAddress("moscow pushkina");
        acc.setWorkAddress("moscow colotushkina");
        acc.setEmail("a");
        acc.setPassword("a");
        acc.setIcq(666666666);
        acc.setSkype("lolir");
        acc.setAdditionalInfo("loliryu");
        ad.create(acc);
        Account ac = ad.read("a");
        System.out.println(ac.getId());
    }
}