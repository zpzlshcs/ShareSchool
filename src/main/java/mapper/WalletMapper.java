package mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Wallet;
import po.WalletCustom;

public interface WalletMapper {
    int deleteByPrimaryKey(Integer walletId);

    int insert(Wallet record);

    int insertSelective(Wallet record);

    Wallet selectByPrimaryKey(Integer walletId);

    int updateByPrimaryKeySelective(Wallet record);

    int updateByPrimaryKey(Wallet record);

    List<WalletCustom> selectWalletSelective(Wallet record);
    
    List<WalletCustom> selectWalletListSelective(Wallet record);
    
    List<WalletCustom> selectWalletListSelectiveByDate
    (@Param("endTime")Date endDate,@Param("startTime")Date startDate,@Param("Wallet")Wallet record);
}