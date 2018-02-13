package com.v41.tp1.modele;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

public enum PeriodicTable
{
	INSTANCE;
	
	/**
	 * Unique list of all the loaded elements
	 */
	private TreeMap<String, ChemicalElement> elements;
	
	/**
	 * Private constructor
	 */
	private PeriodicTable()
	{
		elements = new TreeMap<String, ChemicalElement>();
	}
	
	/**
	 * Loads a periodic table
	 */
	public void loadTableFromString()
	{
		BufferedReader br = null;

		final String data = "Hydrogen,H,1,1.00794\n" +
				"Helium,He,2,4.002602\n" +
				"Lithium,Li,3,6.941\n" +
				"Beryllium,Be,4,9.012182\n" +
				"Boron,B,5,10.811\n" +
				"Carbon,C,6,12.0107\n" +
				"Nitrogen,N,7,14.0067\n" +
				"Oxygen,O,8,15.9994\n" +
				"Fluorine,F,9,18.9984032\n" +
				"Neon,Ne,10,20.1797\n" +
				"Sodium,Na,11,22.98976928\n" +
				"Magnesium,Mg,12,24.305\n" +
				"Aluminum,Al,13,26.9815386\n" +
				"Silicon,Si,14,28.0855\n" +
				"Phosphorus,P,15,30.973762\n" +
				"Sulfur,S,16,32.065\n" +
				"Chlorine,Cl,17,35.453\n" +
				"Argon,Ar,18,39.948\n" +
				"Potassium,K,19,39.0983\n" +
				"Calcium,Ca,20,40.078\n" +
				"Scandium,Sc,21,44.955912\n" +
				"Titanium,Ti,22,47.867\n" +
				"Vanadium,V,23,50.9415\n" +
				"Chromium,Cr,24,51.9961\n" +
				"Manganese,Mn,25,54.938045\n" +
				"Iron,Fe,26,55.845\n" +
				"Cobalt,Co,27,58.933195\n" +
				"Nickel,Ni,28,58.6934\n" +
				"Copper,Cu,29,63.546\n" +
				"Zinc,Zn,30,65.38\n" +
				"Gallium,Ga,31,69.723\n" +
				"Germanium,Ge,32,72.64\n" +
				"Arsenic,As,33,74.9216\n" +
				"Selenium,Se,34,78.96\n" +
				"Bromine,Br,35,79.904\n" +
				"Krypton,Kr,36,83.798\n" +
				"Rubidium,Rb,37,85.4678\n" +
				"Strontium,Sr,38,87.62\n" +
				"Yttrium,Y,39,88.90585\n" +
				"Zirconium,Zr,40,91.224\n" +
				"Niobium,Nb,41,92.90638\n" +
				"Molybdenum,Mo,42,95.96\n" +
				"Technetium,Tc,43,98\n" +
				"Ruthenium,Ru,44,101.07\n" +
				"Rhodium,Rh,45,102.9055\n" +
				"Palladium,Pd,46,106.42\n" +
				"Silver,Ag,47,107.8682\n" +
				"Cadmium,Cd,48,112.411\n" +
				"Indium,In,49,114.818\n" +
				"Tin,Sn,50,118.71\n" +
				"Antimony,Sb,51,121.76\n" +
				"Tellurium,Te,52,127.6\n" +
				"Iodine,I,53,126.90447\n" +
				"Xenon,Xe,54,131.293\n" +
				"Cesium,Cs,55,132.9054519\n" +
				"Barium,Ba,56,137.327\n" +
				"Lanthanum,La,57,138.90547\n" +
				"Cerium,Ce,58,140.116\n" +
				"Praseodymium,Pr,59,140.90765\n" +
				"Neodymium,Nd,60,144.242\n" +
				"Promethium,Pm,61,145\n" +
				"Samarium,Sm,62,150.36\n" +
				"Europium,Eu,63,151.964\n" +
				"Gadolinium,Gd,64,157.25\n" +
				"Terbium,Tb,65,158.92535\n" +
				"Dysprosium,Dy,66,162.5\n" +
				"Holmium,Ho,67,164.93032\n" +
				"Erbium,Er,68,167.259\n" +
				"Thulium,Tm,69,168.93421\n" +
				"Ytterbium,Yb,70,173.054\n" +
				"Lutetium,Lu,71,174.9668\n" +
				"Hafnium,Hf,72,178.49\n" +
				"Tantalum,Ta,73,180.94788\n" +
				"Wolfram,W,74,183.84\n" +
				"Rhenium,Re,75,186.207\n" +
				"Osmium,Os,76,190.23\n" +
				"Iridium,Ir,77,192.217\n" +
				"Platinum,Pt,78,195.084\n" +
				"Gold,Au,79,196.966569\n" +
				"Mercury,Hg,80,200.59\n" +
				"Thallium,Tl,81,204.3833\n" +
				"Lead,Pb,82,207.2\n" +
				"Bismuth,Bi,83,208.9804\n" +
				"Polonium,Po,84,210\n" +
				"Astatine,At,85,210\n" +
				"Radon,Rn,86,222\n" +
				"Francium,Fr,87,223\n" +
				"Radium,Ra,88,226\n" +
				"Actinium,Ac,89,227\n" +
				"Thorium,Th,90,232.03806\n" +
				"Protactinium,Pa,91,231.03588\n" +
				"Uranium,U,92,238.02891\n" +
				"Neptunium,Np,93,237\n" +
				"Plutonium,Pu,94,244\n" +
				"Americium,Am,95,243\n" +
				"Curium,Cm,96,247\n" +
				"Berkelium,Bk,97,247\n" +
				"Californium,Cf,98,251\n" +
				"Einsteinium,Es,99,252\n" +
				"Fermium,Fm,100,257\n" +
				"Mendelevium,Md,101,258\n" +
				"Nobelium,No,102,259\n" +
				"Lawrencium,Lr,103,262\n" +
				"Rutherfordium,Rf,104,261\n" +
				"Dubnium,Db,105,262\n" +
				"Seaborgium,Sg,106,266\n" +
				"Bohrium,Bh,107,264\n" +
				"Hassium,Hs,108,267\n" +
				"Meitnerium,Mt,109,268\n" +
				"Darmstadtium,Ds,110,271\n" +
				"Roentgenium,Rg,111,272\n" +
				"Copernicium,Cn,112,285\n" +
				"Nihonium,Nh,113,284\n" +
				"Flerovium,Fl,114,289\n" +
				"Moscovium,Mc,115,288\n" +
				"Livermorium,Lv,116,292\n" +
				"Tennessine,Ts,117,295\n" +
				"Oganesson,Og,118,294";

		try
		{
			InputStream is = new ByteArrayInputStream(data.getBytes()); //https://www.mkyong.com/java/how-to-convert-string-to-inputstream-in-java/
			br = new BufferedReader(new InputStreamReader(is));
			
			String line = br.readLine();
			
			while (line != null)
			{
				ChemicalElement element = new ChemicalElement();
				element.loadElementFromString(line);
				
				if (element.getAtomicWeight() > 0)
				{
					elements.put(element.getSymbol(), element);
				}
				
				line = br.readLine();
			}
			
			br.close();
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Gets the chemical element according to a string
	 * @param symbol The symbol
	 * @return The chemical element
	 */
	ChemicalElement getChemicalElement(String symbol)
	{
		return elements.get(symbol);
	}
	
	/**
	 * Tests to see if the symbol is a valid chemical element
	 * @param symbol The symbol
	 * @return True if valid
	 */
	public boolean isSymbol(String symbol)
	{
		ChemicalElement valeur = elements.get(symbol);
		
		if (valeur == null)
		{
			return false;
		}
		return true;
	}
}
