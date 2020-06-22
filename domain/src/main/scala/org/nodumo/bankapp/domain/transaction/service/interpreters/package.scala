package org.nodumo.bankapp.domain.transaction.service

package object interpreters {

  // --- Factory instances

  object factoryCustomerIdService extends FactoryCustomerIdServiceInterpreter

  object factoryLoadAmountService extends FactoryLoadAmountServiceInterpreter

  object factoryTimeStampService extends FactoryTimeStampServiceInterpreter

  object factoryTransactionIdService extends FactoryTransactionIdServiceInterpreter

  object factoryCreditTransactionService
      extends FactoryCreditTransactionServiceInterpreter(factoryTransactionIdService,
                                                         factoryCustomerIdService,
                                                         factoryLoadAmountService,
                                                         factoryTimeStampService)

  // --- Validation instances

  object validateDailyCreditAmountLimitServiceEither extends ValidateDailyCreditAmountLimitServiceEitherInterpreter

  object validateDailyTransactionCountLimitServiceEither
      extends ValidateDailyTransactionCountLimitServiceEitherInterpreter

  object validateWeeklyAmountLimitServiceEither extends ValidateWeeklyAmountLimitServiceEitherInterpreter

  object validateTransactionServiceEitherInterpreter
      extends ValidateTransactionServiceEitherInterpreter(validateDailyCreditAmountLimitServiceEither,
                                                          validateDailyTransactionCountLimitServiceEither,
                                                          validateWeeklyAmountLimitServiceEither)

}
