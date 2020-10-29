package com.wallet.WalletItemTest.repository;

import com.wallet.enums.TypeEnum;
import com.wallet.wallet.entities.Wallet;
import com.wallet.wallet.repositories.WalletRepository;
import com.wallet.walletItem.entities.WalletItem;
import com.wallet.walletItem.repositories.WalletItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class WalletItemRepositoryTest {

    private static final Date DATE = new Date();
    private static final TypeEnum TYPE = TypeEnum.IN;
    private static final String DESCRIPTION = "Light bill";
    private static final BigDecimal VALUE = BigDecimal.valueOf(65);

    @Autowired
    private WalletItemRepository walletItemRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Test
    @DisplayName("it should be able to save a new wallet item")
    public void testSave(){
        // Terá que ser vinculado uma carteira
        Wallet wallet = new Wallet();
        wallet.setName("Wallet 1");
        wallet.setValue(BigDecimal.valueOf(500));
        walletRepository.save(wallet);

        WalletItem walletItem = new WalletItem(1l, wallet ,DATE, DESCRIPTION, TYPE, VALUE);

        WalletItem walletItemSaved = walletItemRepository.save(walletItem);

        Assertions.assertThat(walletItemSaved).isNotNull();
        Assertions.assertThat(walletItemSaved.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(walletItemSaved.getType()).isEqualTo(TYPE);
        Assertions.assertThat(walletItemSaved.getValue()).isEqualTo(VALUE);
        Assertions.assertThat(walletItemSaved.getWallet().getId()).isEqualTo(wallet.getId());

    }
}
