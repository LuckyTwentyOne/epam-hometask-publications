package ua.kh.butov.subpub.repository.impl;

import ua.kh.butov.subpub.entity.Voucher;
import ua.kh.butov.subpub.factory.JDBCConnectionUtils;
import ua.kh.butov.subpub.handler.DefaultUniqueResultSetHandler;
import ua.kh.butov.subpub.handler.ResultSetHandler;
import ua.kh.butov.subpub.jdbc.JDBCUtils;
import ua.kh.butov.subpub.repository.VoucherRepository;

public class VoucherRepositoryImpl implements VoucherRepository {

	private final ResultSetHandler<Voucher> vaucherResultSetHandler = new DefaultUniqueResultSetHandler<>(
			Voucher.class);

	@Override
	public Voucher findByCode(Long code) {
		return JDBCUtils.select(JDBCConnectionUtils.getCurrentConnection(), "select * from voucher where code = ?",
				vaucherResultSetHandler, code);
	}

	@Override
	public void deactivateVaucher(Voucher voucher) {
		JDBCUtils.update(JDBCConnectionUtils.getCurrentConnection(), "update voucher set active=? where id=?", false, voucher.getId());	
	}
}
