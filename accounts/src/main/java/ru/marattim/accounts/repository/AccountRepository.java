package ru.marattim.accounts.repository;

import jakarta.persistence.LockModeType;
import org.openapitools.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.marattim.accounts.entity.AccountEntity;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsByCustomerIdAndCurrency(long customerId, Currency currency);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    default Optional<AccountEntity> findByIdWithLock(long id) {
        return findById(id);
    }
}
