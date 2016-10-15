package com.brokersystems.invtransactions.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="sys_receipts_settlements")
public class ReceiptSettlementDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rec_settle_id")
	private Long settlementId;
	
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="rec_settle_invoice")
	private TenantInvoice invoice;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="rec_settle_dr")
	private Transactions debit;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="rec_settle_cr")
	private Transactions credit;
	
	@Column(name="rec_alloc_amt")
	private BigDecimal allocatedAmt;

	public Long getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Long settlementId) {
		this.settlementId = settlementId;
	}

	public TenantInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(TenantInvoice invoice) {
		this.invoice = invoice;
	}

	public Transactions getDebit() {
		return debit;
	}

	public void setDebit(Transactions debit) {
		this.debit = debit;
	}

	public Transactions getCredit() {
		return credit;
	}

	public void setCredit(Transactions credit) {
		this.credit = credit;
	}

	public BigDecimal getAllocatedAmt() {
		return allocatedAmt;
	}

	public void setAllocatedAmt(BigDecimal allocatedAmt) {
		this.allocatedAmt = allocatedAmt;
	}
	
	

}
