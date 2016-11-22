package eu.sig.training.ch04;

import eu.sig.training.ch04.Money;

// tag::CheckingAccount[]
public class CheckingAccount extends Accounts{
    private int transferLimit = 100;

    public Transfer makeTransfer(String counterAccount, Money amount)
        throws BusinessException {

        checkWithdrawalLimit(amount);

        boolean valid = isValid(counterAccount);

        if (valid) {
            return lookUpCounterAccountAndMakeTransferObject(counterAccount, amount);
        } else {
            throw new BusinessException("Invalid account number!");
        }
    }

    private void checkWithdrawalLimit(Money amount) throws BusinessException{
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
