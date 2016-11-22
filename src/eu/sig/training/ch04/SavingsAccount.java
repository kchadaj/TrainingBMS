package eu.sig.training.ch04;

import eu.sig.training.ch04.Money;

// tag::SavingsAccount[]
public class SavingsAccount extends Accounts{
    CheckingAccount registeredCounterAccount;

    public Transfer makeTransfer(String counterAccount, Money amount) 
        throws BusinessException {
        // 1. Assuming result is 9-digit bank account number, validate 11-test:
        boolean valid = isValid(counterAccount);

        if (valid) {
            Transfer result = makeTransferObject(counterAccount, amount);
            return checkIfWithdrawalIsToRegisterCounterAccount(result);

        } else {
            throw new BusinessException("Invalid account number!!");
        }
    }

    private Transfer makeTransferObject(String counterAccount, Money amount) {
        CheckingAccount acct = Accounts.findAcctByNumber(counterAccount);
        Transfer result = new Transfer(this, acct, amount); // <2>
        return result;
    }

    private Transfer checkIfWithdrawalIsToRegisterCounterAccount(Transfer result) throws BusinessException{
        if (result.getCounterAccount().equals(this.registeredCounterAccount))
        {
            return result;
        } else {
            throw new BusinessException("Counter-account not registered!");
        }
    }



}
// end::SavingsAccount[]
