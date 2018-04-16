package com.getjavajob.pastukhovm.service;

import com.getjavajob.AccountDAO;
import com.getjavajob.AccountService;
import com.getjavajob.common.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountDAO accountDao;

    @InjectMocks
    private AccountService accountService;

    public Account createAccountForTest() {
        Account account = new Account();
        account.setImage(new byte[]{1, 2, 3, 4, 5, 6});
        Phone phoneOne = new Phone();
        phoneOne.setPhoneType(PhoneType.WORK);
        phoneOne.setValue("+7895199923");
        phoneOne.setOwner(new Account());
        Phone phoneTwo = new Phone();
        phoneTwo.setPhoneType(PhoneType.HOME);
        phoneTwo.setValue("+74956731223");
        phoneTwo.setOwner(new Account());
        List<Phone> phones = asList(phoneOne, phoneTwo);

        account.setImage(image);
        account.setPhones(phones);
        account.setLastName("Ivanov");
        account.setName("Ivan");
        account.setBirthDate(LocalDate.of(1970, 2, 12));
        account.setEmail("ivanov@mail.ru");
        account.setPassword("password");
        account.setGender(Gender.MALE);
        return account;
    }

    @Test
    public void saveAccountCreate() throws Exception {
        Account account = createAccountForTest();
        Answer accountModifier = (Answer<Account>) invocationOnMock -> {
            Account account1 = (Account) invocationOnMock.getArguments()[0];
            account1.setId(1L);
            return null;
        };
        doAnswer(accountModifier).when(accountDao).create(account);
        accountService.saveAccount(account);
        verify(accountDao).create(account);
    }

    @Test
    public void saveAccountUpdate() throws Exception {
        Account account = createAccountForTest();
        account.setId(1L);
        accountService.saveAccount(account);
        verify(accountDao).update(account);
    }

    @Test
    public void getAccountById() throws Exception {
        Account account = createAccountForTest();
        when(accountDao.getByKey(1L)).thenReturn(account);
        Account actual = accountService.getAccountById(1L);
        Account expected = account;
        verify(accountDao).getByKey(1L);
        assertAccountsEqual(expected, actual);
    }

    @Test
    public void getAccountsByNameLike() throws Exception {
        Account account = createAccountForTest();
        when(accountDao.getByNameLike("ivan")).thenReturn(asList(account));
        List<Account> actual = accountService.getAccountsByNameLike("ivan");
        List<Account> expected = asList(account);
        verify(accountDao).getByNameLike("ivan");
        assertEquals(actual.size(), expected.size());
        for (int i = 0; i < actual.size(); i++) {
            assertAccountsEqual(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void getAccountDtosByNameLike() {
        Account account = createAccountForTest();
        when(accountDao.getByNameLike("ivan")).thenReturn(asList(account));
        List<AccountDto> actual = accountService.getAccountDtosByNameLike("ivan");
        List<AccountDto> expected = asList(new AccountDto(account));

        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getId(), actual.get(i).getId());
            assertEquals(expected.get(i).getLastName(), actual.get(i).getLastName());
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
        }
        verify(accountDao).getByNameLike("ivan");

    }

    @Test
    public void getAccountByCredentials() throws Exception {
        Account account = createAccountForTest();
        when(accountDao.getByCredentials("ivanov@mail.ru", "password")).thenReturn(account);
        Account actual = accountService.getAccountByCredentials("ivanov@mail.ru", "password");
        Account expected = account;
        verify(accountDao).getByCredentials("ivanov@mail.ru", "password");
        assertAccountsEqual(expected, actual);
    }

    private static void assertAccountsEqual(Account expected, Account actual) {
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getGender(), actual.getGender());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getBirthDate(), actual.getBirthDate());
        assertEquals(expected.getSkype(), actual.getSkype());
        assertEquals(expected.getIcq(), actual.getIcq());
        assertEquals(expected.getHomeAddress(), actual.getHomeAddress());
        assertEquals(expected.getWorkAddress(), actual.getWorkAddress());
        assertEquals(expected.getInfo(), actual.getInfo());
        assertEquals(expected.getEmail(), actual.getEmail());
    }
}