package eu.sig.training.ch04;

import eu.sig.training.ch04.Money;

// tag::SavingsAccount[]
public class SavingsAccount {
    CheckingAccount registeredCounterAccount;

    public Transfer makeTransfer(String counterAccount, Money amount) 
        throws BusinessException {
        // 1. Assuming result is 9-digit bank account number, validate 11-test:
        int sum = 0; // <1>
        for (int i = 0; i < counterAccount.length(); i++) {
            sum = sum + (9 - i) * Character.getNumericValue(
                counterAccount.charAt(i));
        }
        if (sum % 11 == 0) {
            Transfer result = makeTransferObject(counterAccount, amount);
            return checkIfWithdrawalIsToRegisterCounterAccount(result);

        } else {
            throw new BusinessException("Invalid account number!!");
        }
    }

    private Transfer makeTransferObject(String counterAccount, Money amount) {
        // 2. Look up counter account and make transfer object:
        CheckingAccount acct = Accounts.findAcctByNumber(counterAccount);
        Transfer result = new Transfer(this, acct, amount); // <2>
        return result;
    }

    private Transfer checkIfWithdrawalIsToRegisterCounterAccount(Transfer result) throws BusinessException{
        // 3. Check whether withdrawal is to registered counter account:
        if (result.getCounterAccount().equals(this.registeredCounterAccount))
        {
            return result;
        } else {
            throw new BusinessException("Counter-account not registered!");
        }
    }



}
// end::SavingsAccount[]
