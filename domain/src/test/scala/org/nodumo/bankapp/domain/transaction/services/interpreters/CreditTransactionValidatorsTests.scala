package org.nodumo.bankapp.domain.transaction.services.interpreters

import org.joda.time.DateTime
import org.nodumo.bankapp.domain.transaction.model.credittransaction.{
  CreditTransaction,
  CustomerId,
  LoadAmount,
  TimeStamp,
  TransactionId
}
import org.nodumo.bankapp.domain.transaction.service.interpreters.ValidateDailyCreditAmountLimitServiceEitherInterpreter.DAILY_MAXIMUM_CREDIT_AMOUNT
import org.nodumo.bankapp.domain.transaction.service.interpreters.ValidateDailyTransactionCountLimitServiceEitherInterpreter.DAILY_MAXIMUM_TRANSACTION_COUNT
import org.nodumo.bankapp.domain.transaction.service.interpreters.ValidateWeeklyAmountLimitServiceEitherInterpreter.WEEKLY_MAXIMUM_CREDIT_AMOUNT
import org.nodumo.bankapp.domain.transaction.service.interpreters.{
  validateDailyCreditAmountLimitServiceEither,
  validateDailyTransactionCountLimitServiceEither,
  validateWeeklyAmountLimitServiceEither
}
import org.specs2.Specification

class CreditTransactionValidatorsTests extends Specification {
  def is = s2"""

  This is a specification for the 'Credit Transaction Validators' services

    Limit on count of transactions in a day (At limit)                    $dailyTransactionsCountFailureAtLimit
    Limit on count of transactions in a day (Minus one under limit)       $dailyTransactionsCountSuccessMinusOne
    Limit on count of transactions in a day (Plus one over limit)         $dailyTransactionsCountFailurePlusOne

    Limit on count of transactions in a day (At limit)                    $dailyTransactionsAmountSuccessAtLimit
    Limit on count of transactions in a day (Minus one under limit)       $dailyTransactionsAmountSuccessMinusOne
    Limit on count of transactions in a day (Plus one over limit)         $dailyTransactionsAmountFailurePlusOne

    Limit on amount of transactions money in a week (At limit)                $WeeklyTransactionsAmountSuccessAtLimit
    Limit on amount of transactions money in a week (Minus one under limit)   $WeeklyTransactionsAmountSuccessMinusOne
    Limit on amount of transactions money in a week (Plus one over limit)     $WeeklyTransactionsAmountFailurePlusOne

                                                      """

  private lazy val anyCreditTransactionAtMaximum = createTransaction(DAILY_MAXIMUM_CREDIT_AMOUNT)

  // Tests daily transaction count

  private lazy val dailyAmountListMaximumForLimitMinusOne =
    List(DAILY_MAXIMUM_CREDIT_AMOUNT)

  // Tests weekly transaction count

  private lazy val amount2List =
    List(1L, 1L)

  private def createTransaction(loadAmount: LoadAmount.Repr) = CreditTransaction(
    TransactionId("1234"),
    CustomerId("1234"),
    LoadAmount(loadAmount),
    TimeStamp(DateTime.now())
  )
  private def createListOfTransaction(list: List[LoadAmount.Repr]): List[CreditTransaction] =
    list.map(createTransaction)

  // ---  Daily count specs

  private def dailyTransactionsCountSuccessMinusOne =
    validateDailyTransactionCountLimitServiceEither.validate(anyCreditTransactionAtMaximum,
                                                             createListOfTransaction(
                                                               List.fill(DAILY_MAXIMUM_TRANSACTION_COUNT - 1)(
                                                                 DAILY_MAXIMUM_CREDIT_AMOUNT))) must be right

  // If you are at the limit, the new one will break limit
  private def dailyTransactionsCountFailureAtLimit =
    validateDailyTransactionCountLimitServiceEither.validate(
      anyCreditTransactionAtMaximum,
      createListOfTransaction(List.fill(DAILY_MAXIMUM_TRANSACTION_COUNT)(DAILY_MAXIMUM_CREDIT_AMOUNT))) must be left

  private def dailyTransactionsCountFailurePlusOne =
    validateDailyTransactionCountLimitServiceEither.validate(
      anyCreditTransactionAtMaximum,
      createListOfTransaction(List.fill(DAILY_MAXIMUM_TRANSACTION_COUNT + 1)(DAILY_MAXIMUM_CREDIT_AMOUNT))) must be left

  // ---  Daily amount specs

  private def dailyTransactionsAmountSuccessAtLimit =
    validateDailyCreditAmountLimitServiceEither.validate(createTransaction(DAILY_MAXIMUM_CREDIT_AMOUNT - 2),
                                                         createListOfTransaction(amount2List)) must be right

  private def dailyTransactionsAmountSuccessMinusOne =
    validateDailyCreditAmountLimitServiceEither.validate(createTransaction(DAILY_MAXIMUM_CREDIT_AMOUNT - 3),
                                                         createListOfTransaction(amount2List)) must be right

  private def dailyTransactionsAmountFailurePlusOne =
    validateDailyCreditAmountLimitServiceEither.validate(createTransaction(DAILY_MAXIMUM_CREDIT_AMOUNT - 1),
                                                         createListOfTransaction(amount2List)) must be left
  // ---  Weekly amount specs

  private def WeeklyTransactionsAmountSuccessAtLimit =
    validateWeeklyAmountLimitServiceEither.validate(createTransaction(WEEKLY_MAXIMUM_CREDIT_AMOUNT - 2),
                                                    createListOfTransaction(amount2List)) must be right

  private def WeeklyTransactionsAmountSuccessMinusOne =
    validateWeeklyAmountLimitServiceEither.validate(createTransaction(WEEKLY_MAXIMUM_CREDIT_AMOUNT - 3),
                                                    createListOfTransaction(amount2List)) must be right

  private def WeeklyTransactionsAmountFailurePlusOne =
    validateWeeklyAmountLimitServiceEither.validate(
      createTransaction(WEEKLY_MAXIMUM_CREDIT_AMOUNT - 1),
      createListOfTransaction(dailyAmountListMaximumForLimitMinusOne)) must be left

}
