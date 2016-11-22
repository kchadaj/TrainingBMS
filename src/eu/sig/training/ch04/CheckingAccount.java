package eu.sig.training.ch04;

import eu.sig.training.ch04.Money;

// tag::CheckingAccount[]
public class CheckingAccount {
    private int transferLimit = 100;

    public Transfer makeTransfer(String counterAccount, Money amount)
        throws BusinessException {

        checkWithdrawalLimit(amount);

        // 2. Assuming result is 9-digit bank account number, validate 11-test:
        int sum = 0;
        for (int i = 0; i < counterAccount.length(); i++) {
            sum = sum + (9-i) * Character.getNumericValue(
                counterAccount.charAt(i));
        }
        if (sum % 11 == 0) {
            return lookUpCounterAccountAndMakeTransferObject(counterAccount, amount);
        } else {
            throw new BusinessException("Invalid account number!");
        }
    }

    private void checkWithdrawalLimit(Money amount) throws BusinessException{
        // 1. Check withdrawal limit:
        if (amount.greaterThan(this.transferLimit)) {
            throw new BusinessException("Limit exceeded!");
        }
    }

    private Transfer lookUpCounterAccountAndMakeTransferObject(String counterAccount, Money amount) {
        CheckingAccount acct = Accounts.findAcctByNumber(counterAccount);
        Transfer result = new Transfer(this, acct, amount);
        return result;
    }


}
// end::CheckingAccount[]
