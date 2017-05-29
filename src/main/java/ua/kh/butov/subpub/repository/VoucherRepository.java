package ua.kh.butov.subpub.repository;

import ua.kh.butov.subpub.entity.Voucher;

public interface VoucherRepository {
	
	Voucher findByCode(Long code);
	
	void deactivateVaucher(Voucher voucher);

}
