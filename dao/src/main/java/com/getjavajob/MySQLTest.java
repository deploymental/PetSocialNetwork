package com.getjavajob;

import com.getjavajob.common.Account;
import com.getjavajob.common.enums.Sex;

import java.sql.Date;
import java.sql.SQLException;

public class MySQLTest {
    public static void main(String[] args) throws SQLException {
        AccountDAO ad = new AccountDAO();
        Account forTests = new Account();
        forTests.setId(50);
        forTests.setName("Vasya");
        forTests.setMiddleName("Vasylevich");
        forTests.setSurName("Vasyacovich");
        forTests.setSex(Sex.valueOf("MALE"));
        forTests.setBirthDate(new Date(2134235));
        forTests.setHomeAddress("moscow pushkina");
        forTests.setWorkAddress("moscow colotushkina");
        forTests.setEmail("loh@pidr.ua");
        forTests.setIcq(666666666);
        forTests.setSkype("lolir");
        forTests.setAdditionalInfo("loliryu");

        /*ad.create(forTests);
        forTests.setSkype("pokemon");
        ad.update(forTests);
        System.out.println(ad.getAll().size());
        System.out.println(ad.read(50).getSkype());
        ad.delete(50);
        System.out.println(ad.getAll().size());*/
        System.out.println(ad.getAll());
    }
}