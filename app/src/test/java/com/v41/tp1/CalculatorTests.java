package com.v41.tp1;

import com.v41.tp1.modele.ChemicalFormula;
import com.v41.tp1.modele.PeriodicTable;
import com.v41.tp1.vuecontroleur.ChemicalValidator;
import com.v41.tp1.vuecontroleur.StringWrapper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Doge on 13-Feb-18.
 */

public class CalculatorTests
{

	private final double PRECISION = 0.005;  //Si jamais ça ne passe pas descendez la précision
	// 0.01 devrait fonctionner avec tout le monde

	@Test
	public void A_initialiser_AvecComposeChimiqueValideSansParenthese_DevraitRetournerVrai() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper messageUtilisateur = new StringWrapper();

		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("H"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("H2O"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("NaCl"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("O2"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("C11H23COOH"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("C12H22O11"), messageUtilisateur));
	}

	@Test
	public void B_initialiser_AvecComposeChimiqueValideContenantParenthesesNonImbriquees_DevraitRetournerVrai() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper messageUtilisateur = new StringWrapper();

		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H)"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(NaCl)4"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(O2)3"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(CN)4(O2)3"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("CO(CH2OH)2"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("C18H24N2O6"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("NaCl(O3H5)2H4"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(CH3)2CO"), messageUtilisateur));
	}

	@Test
	public void C_initialiser_AvecComposeChimiqueValideContenantParenthesesImbriquees_DevraitRetournerVrai() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper messageUtilisateur = new StringWrapper();

		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H(CN)4)5"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("B(Ar(CF3)2)4"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("B(Ar(CF3)2)4"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("((MgFe)7Si8(OH)22)"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(Al2Si2O5(OH)4)"), messageUtilisateur));
		assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2SO4(Be)3(H2O))2"), messageUtilisateur));
	}

	@Test
	public void D_initialiser_AvecMauvaiseUtilisationParentheses_DevraitRetournerFaux() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper messageUtilisateur = new StringWrapper();

		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((")("), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H2("), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H)O("), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(NaCl))"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("((NaCl)"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((")(H2N2)4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("()H2N2)4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2)N2)4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2))4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2)4)"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("((H2N2)4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2(N2)4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2()4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2)(4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2)4("), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("()"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(())"), messageUtilisateur));
	}

	@Test
	public void E_initialiser_AvecComposesChimiquesInvalides_DevraitRetournerFaux() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper messageUtilisateur = new StringWrapper();

		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("h"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("nacl"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("CH1"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("Al0"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("BONDs007"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H002O"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("al"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("fe"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("Hu"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("abc"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((" "), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("!"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H 2"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("Na+"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("3Al"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("3(Al)4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(3Al)4"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(3Al)"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("((3Al))"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("9"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(3)2"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("2(3)"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H2 O2"), messageUtilisateur));
		assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((""), messageUtilisateur));
	}

	@Test
	public void F_getWeight_AvecChemicalCompositionSansParenthese_DevraitRetournerLePoidsCorrespondant() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper sw = new StringWrapper();
		ChemicalFormula composeChimique = new ChemicalFormula();

		ChemicalValidator.INSTANCE.validateChemicalFormula("H", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 1.007947, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("H2O", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 18.01532, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("NaCl", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 58.4430, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("O2", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 31.99886, PRECISION);
	}

	@Test
	public void G_getWeight_AvecChemicalCompositionContenantParenthesesNonImbriquees_DevraitRetournerLePoidsCorrespondant() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper sw = new StringWrapper();
		ChemicalFormula composeChimique = new ChemicalFormula();

		ChemicalValidator.INSTANCE.validateChemicalFormula("(NaCl)4", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 233.7719, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("(O2)3", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 95.99658, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("CO(CH2OH)2", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 90.07831, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("(CH3)2CO", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 58.07945, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("NaCl(O3H5)2H4", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 168.5508, PRECISION);
	}

	@Test
	public void H_getWeight_AvecChemicalCompositionContenantParenthesesImbriquees_DevraitRetournerLePoidsCorrespondant() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper sw = new StringWrapper();
		ChemicalFormula composeChimique = new ChemicalFormula();

		ChemicalValidator.INSTANCE.validateChemicalFormula("(H(CN)4)5", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 525.3897, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("(CN)4(O2)3", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 200.0666, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("B(Ar(CF3)2)4", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 722.6520, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("(Al2Si2O5(OH)4)", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 258.1608, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("(Ca4Si2O6(CO3)(OHF))2", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 816.9922, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("(H2SO4(Be)3(H2O))2", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 286.2620, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("B(Ar(CF3)2)4", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 722.6520, PRECISION);
	}

	@Test
	public void I_getWeight_AvecChemicalCompositionContenantNombrePlusGrandQue9_DevraitRetournerLePoidsCorrespondant() throws Exception
	{
		PeriodicTable.INSTANCE.loadTableFromString();
		StringWrapper sw = new StringWrapper();
		ChemicalFormula composeChimique = new ChemicalFormula();

		ChemicalValidator.INSTANCE.validateChemicalFormula("((MgFe)7Si8(OH)22)", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 1159.8984, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("(C18H24N2O6)", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 364.3948, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("C11H23COOH", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 200.3189, PRECISION);

		ChemicalValidator.INSTANCE.validateChemicalFormula("C12H22O11", sw);
		composeChimique.init(ChemicalValidator.INSTANCE.getTokens());
		assertEquals(composeChimique.getWeight(), 342.2979, PRECISION);
	}
}
