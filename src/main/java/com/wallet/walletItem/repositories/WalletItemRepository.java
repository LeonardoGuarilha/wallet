package com.wallet.walletItem.repositories;

import com.wallet.walletItem.entities.WalletItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletItemRepository extends JpaRepository<WalletItem, Long> {
}
