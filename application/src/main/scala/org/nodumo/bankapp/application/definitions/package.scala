package org.nodumo.bankapp.application

package object definitions {

  type EitherTransactionError[A] = Either[TransactionError, A]

}
