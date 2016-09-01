package com.brokersystems.setups.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.action.internal.QueuedOperationCollectionAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setup.repository.CountryRepository;
import com.brokersystems.setup.repository.CountyRepository;
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setup.repository.OrgBranchRepository;
import com.brokersystems.setup.repository.PaymentModeRepo;
import com.brokersystems.setup.repository.RateTypeRepository;
import com.brokersystems.setup.repository.RentalStructRepository;
import com.brokersystems.setup.repository.RentalUnitChargeRepo;
import com.brokersystems.setup.repository.RentalUnitsRepository;
import com.brokersystems.setup.repository.TownRepository;
import com.brokersystems.setup.repository.UnitTypeRepository;
import com.brokersystems.setups.model.Country;
import com.brokersystems.setups.model.County;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.OrgRegions;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.QCountry;
import com.brokersystems.setups.model.QCounty;
import com.brokersystems.setups.model.QCurrencies;
import com.brokersystems.setups.model.QOrgBranch;
import com.brokersystems.setups.model.QOrgRegions;
import com.brokersystems.setups.model.QPaymentModes;
import com.brokersystems.setups.model.QRateTypes;
import com.brokersystems.setups.model.QRentalStructure;
import com.brokersystems.setups.model.QRentalUnitCharges;
import com.brokersystems.setups.model.QRentalUnits;
import com.brokersystems.setups.model.QTown;
import com.brokersystems.setups.model.QUnitTypes;
import com.brokersystems.setups.model.RateTypes;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.brokersystems.setups.model.RentalUnits;
import com.brokersystems.setups.model.Town;
import com.brokersystems.setups.model.UnitTypes;
import com.brokersystems.setups.service.SetupsService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class SetupsServiceImpl implements SetupsService {

	@Autowired
	private CurrencyRepository currRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private CountyRepository countyRepo;

	@Autowired
	private TownRepository townRepo;

	@Autowired
	private RateTypeRepository rateTypeRepo;

	@Autowired
	private UnitTypeRepository unitTypeRepo;

	@Autowired
	private RentalStructRepository rentalStructRepo;

	@Autowired
	private RentalUnitsRepository rentalUnitRepo;

	@Autowired
	private OrgBranchRepository branchRepo;

	@Autowired
	private RentalUnitChargeRepo unitChargeRepo;
	
	@Autowired
	private PaymentModeRepo payRepo;

	@Override
	@Transactional(readOnly = true)
	public DataTablesResult<Currencies> findAllCurrencies(DataTablesRequest request) throws IllegalAccessException {
		Page<Currencies> page = currRepo.findAll(request.searchPredicate(QCurrencies.currencies), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void defineCurrency(Currencies currency) {

		currRepo.save(currency);

	}

	@Override
	public void deleteCurrency(Long currCode) {
		currRepo.delete(currCode);

	}

	@Override
	public DataTablesResult<Country> findAllCountries(DataTablesRequest request) throws IllegalAccessException {
		Page<Country> page = countryRepo.findAll(request.searchPredicate(QCountry.country), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void defineCountry(Country country) {
		countryRepo.save(country);

	}

	@Override
	public void deleteCountry(Long couCode) {
		countryRepo.delete(couCode);

	}

	@Override
	public DataTablesResult<County> findCountiesByCountry(long couCode, DataTablesRequest request)
			throws IllegalAccessException {
		QCountry country = QCounty.county.country;
		BooleanExpression pred = country.couCode.eq(couCode);
		Page<County> page = countyRepo.findAll(pred.and(request.searchPredicate(QCounty.county)), request);
		return new DataTablesResult(request, page);
	}

	@Override
	public void defineCounty(County county) {
		countyRepo.save(county);

	}

	@Override
	public void deleteCounty(Long countyCode) {
		countyRepo.delete(countyCode);

	}

	@Override
	public DataTablesResult<Town> findTownsByCounty(long countyCode, DataTablesRequest request)
			throws IllegalAccessException {
		QCounty county = QTown.town.county;
		BooleanExpression pred = county.countyId.eq(countyCode);
		Page<Town> page = townRepo.findAll(pred.and(request.searchPredicate(QTown.town)), request);
		return new DataTablesResult(request, page);
	}

	@Override
	public void defineTown(Town town) {
		townRepo.save(town);
	}

	@Override
	public void deleteTown(Long townCode) {
		townRepo.delete(townCode);
	}

	@Override
	public DataTablesResult<RateTypes> findAllRateTypes(DataTablesRequest request) throws IllegalAccessException {
		Page<RateTypes> page = rateTypeRepo.findAll(request.searchPredicate(QRateTypes.rateTypes), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void defineRateType(RateTypes rateType) {
		rateTypeRepo.save(rateType);

	}

	@Override
	public void deleteRateType(Long rateTypeCode) {
		rateTypeRepo.delete(rateTypeCode);

	}

	@Override
	public DataTablesResult<UnitTypes> findAllUnitTypes(DataTablesRequest request) throws IllegalAccessException {
		Page<UnitTypes> page = unitTypeRepo.findAll(request.searchPredicate(QUnitTypes.unitTypes), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void defineUnitType(UnitTypes unitType) {
		unitTypeRepo.save(unitType);

	}

	@Override
	public void deleteUnitType(Long unitCode) {
		unitTypeRepo.delete(unitCode);

	}

	@Override
	public DataTablesResult<RentalStructure> findAllStructures(long branchId, DataTablesRequest request)
			throws IllegalAccessException {
		QOrgBranch orgbranch = QRentalStructure.rentalStructure.branch;
		BooleanExpression pred = orgbranch.obId.eq(branchId);
		Page<RentalStructure> page = rentalStructRepo
				.findAll(pred.and(request.searchPredicate(QRentalStructure.rentalStructure)), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public DataTablesResult<RentalUnits> findAllRentalUnits(long rentalId, DataTablesRequest request)
			throws IllegalAccessException {
		QRentalStructure structure = QRentalUnits.rentalUnits.rentalStruct;
		BooleanExpression pred = structure.rentalId.eq(rentalId);
		Page<RentalUnits> page = rentalUnitRepo.findAll(pred.and(request.searchPredicate(QRentalUnits.rentalUnits)),
				request);
		return new DataTablesResult(request, page);
	}

	@Override
	public RentalStructure defineRentalStruct(RentalStructure struct) {
		RentalStructure s = rentalStructRepo.save(struct);
		return s;

	}

	@Override
	public void deleteRentalStruct(Long structId) {
		rentalStructRepo.delete(structId);

	}

	@Override
	public void defineRentalUnits(RentalUnits unit) throws BadRequestException {
		RentalStructure struct = unit.getRentalStruct();
		if (struct.getNoOfUnits() == struct.getRentalUnits().size()) {
			throw new BadRequestException(
					"Number of Units Defined in the structure cannot be greater than number of units setup");
		}
		rentalUnitRepo.save(unit);

	}

	@Override
	public void deleteRentalUnit(Long unitId) {
		rentalUnitRepo.delete(unitId);

	}

	@Override
	public Page<OrgBranch> findBranchForSelect(String paramString, Pageable paramPageable) {
		Predicate pred = null;
		if (paramString == null || StringUtils.isBlank(paramString)) {
			pred = QOrgBranch.orgBranch.isNotNull();
		} else {
			pred = QOrgBranch.orgBranch.obName.containsIgnoreCase(paramString);
		}
		return branchRepo.findAll(pred, paramPageable);
	}

	@Override
	public Page<UnitTypes> findUnitsForSelect(String paramString, Pageable paramPageable) {
		Predicate pred = null;
		if (paramString == null || StringUtils.isBlank(paramString)) {
			pred = QUnitTypes.unitTypes.isNotNull();
		} else {
			pred = QUnitTypes.unitTypes.unitName.containsIgnoreCase(paramString);
		}
		return unitTypeRepo.findAll(pred, paramPageable);
	}

	@Override
	public RentalStructure getStructureDetails(Long rentalId) {
		return rentalStructRepo.findOne(rentalId);
	}

	@Override
	public DataTablesResult<RentalUnitCharges> findRentalUnitCharges(long renId, DataTablesRequest request)
			throws IllegalAccessException {
		QRentalUnits unitCharges = QRentalUnitCharges.rentalUnitCharges.unit;
		BooleanExpression pred = unitCharges.renId.eq(renId);
		Page<RentalUnitCharges> page = unitChargeRepo
				.findAll(pred.and(request.searchPredicate(QRentalUnitCharges.rentalUnitCharges)), request);
		return new DataTablesResult(request, page);
	}

	@Override
	public void defineRentalCharges(RentalUnitCharges charge) throws BadRequestException {
		if (charge.isTaxable()) {
			if (charge.getTaxValue() == null || charge.getTaxValue().compareTo(BigDecimal.ZERO) == 0) {
				throw new BadRequestException("Enter Taxable Value...");
			}
			if (charge.getTaxRateType().equalsIgnoreCase("P")) {
				if (charge.getTaxValue().compareTo(BigDecimal.ZERO) == -1) {
					throw new BadRequestException("Taxable Value Cannot be less than zero if its a percentage...");
				}
				if (charge.getTaxValue().compareTo(new BigDecimal(100)) == 1) {
					throw new BadRequestException("Taxable Value Cannot be greater than 100 if its a percentage...");
				}
			}
		}
		if (charge.getRateType() == null) {
			throw new BadRequestException("Select Rate Type to continue...");
		}
		Date dateTo = null;
		if (charge.getWetDate() != null) {
			dateTo = charge.getWetDate();
			if (charge.getWefDate().after(charge.getWetDate())) {
				throw new BadRequestException("WEF Date Cannot be greater than WET Date");
			}
		} else {
			dateTo = new Date();
		}

		QRentalUnitCharges unitCharges = QRentalUnitCharges.rentalUnitCharges;
		BooleanExpression exp = unitCharges.rateType.eq(charge.getRateType()).and(unitCharges.wefDate
				.between(charge.getWefDate(), dateTo).or(unitCharges.wetDate.between(charge.getWefDate(), dateTo)));
		long size = unitChargeRepo.findAll(exp).spliterator().getExactSizeIfKnown();
		if (charge.getChargeId() == null) {
			if (size > 0) {
				throw new BadRequestException("Charges for Selected period already exists...");
			}
		} else {
			if (size > 1) {
				throw new BadRequestException("Charges for Selected period already exists...");
			}
		}
		
		BooleanExpression nullDates = unitCharges.rateType.eq(charge.getRateType()).and(unitCharges.wetDate.isNull());
		
		Iterator<RentalUnitCharges> it = unitChargeRepo.findAll(nullDates).iterator();
		while(it.hasNext()){
			RentalUnitCharges unitCharge = (RentalUnitCharges)it.next();
			Calendar cal  = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DAY_OF_YEAR, -1);
			unitCharge.setWetDate(cal.getTime());
			unitChargeRepo.save(unitCharge);
		}

		unitChargeRepo.save(charge);

	}

	@Override
	public void deleteRentalCharge(Long chargeId) {
		unitChargeRepo.delete(chargeId);

	}

	@Override
	public Page<RateTypes> findRatesForSelect(String paramString, Pageable paramPageable) {
		Predicate pred = null;
		if (paramString == null || StringUtils.isBlank(paramString)) {
			pred = QRateTypes.rateTypes.isNotNull();
		} else {
			pred = QRateTypes.rateTypes.rateType.containsIgnoreCase(paramString);
		}
		return rateTypeRepo.findAll(pred, paramPageable);
	}
	
	@Override
	public DataTablesResult<PaymentModes> findAllPaymentModes(DataTablesRequest request) throws IllegalAccessException  {
		Page<PaymentModes> page = payRepo.findAll(request.searchPredicate(QPaymentModes.paymentModes), request);
		return new DataTablesResult<>(request, page);
	}

	@Override
	public void definePaymentMode(PaymentModes mode) throws BadRequestException {
		if(mode.getPmMaxValue().compareTo(mode.getPmMinValue()) ==-1){
			throw new BadRequestException("Max Value cannot be less than min Value");
		}
		payRepo.save(mode);
		
	}

	@Override
	public void deletePaymentMode(Long pmId) {
		payRepo.delete(pmId);
		
	}

}
