package mybank.server.beans;

import java.util.List;





public class LienCommunauteUser implements ObjetId {
	
	String nomClasse=LienCommunauteUser.class.getName();
	String id;
	Communaute communaute;
	User user;
	private Compte compte;
	private TypeObjet typeposition=CacheType.getPremiereInstance("TypePosition");
	private TypeObjet typerole=CacheType.getPremiereInstance("TypeRole");
	private TypeObjet typevisibiliteprofil=CacheType.getPremiereInstance("TypeVisibiliteProfil");
	private boolean modedeclenchementautomatique;
	private int soldeminipourautoriserpret;
	private int montantmaxencourspreter;
	private int montantmaxencoursdonner;
	private int soldeminidemandeappelfond;
	
	private int soldeminiremoubourserappelfond;
	private List<Compte> comptes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Communaute getCommunaute() {
		return communaute;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte comptePrincipale) {
		this.compte = comptePrincipale;
	}
	public TypeObjet getTypeposition() {
		return typeposition;
	}
	public void setTypeposition(TypeObjet typeposition) {
		this.typeposition = typeposition;
	}
	public TypeObjet getTyperole() {
		return typerole;
	}
	public void setTyperole(TypeObjet typerole) {
		this.typerole = typerole;
	}
	public TypeObjet getTypevisibiliteprofil() {
		return typevisibiliteprofil;
	}
	public void setTypevisibiliteprofil(TypeObjet typevisibiliteprofil) {
		this.typevisibiliteprofil = typevisibiliteprofil;
	}
	public boolean isModedeclenchementautomatique() {
		return modedeclenchementautomatique;
	}
	public void setModedeclenchementautomatique(boolean modedeclenchementautomatique) {
		this.modedeclenchementautomatique = modedeclenchementautomatique;
	}
	public int getSoldeminipourautoriserpret() {
		return soldeminipourautoriserpret;
	}
	public void setSoldeminipourautoriserpret(int soldeminipourautoriserpret) {
		this.soldeminipourautoriserpret = soldeminipourautoriserpret;
	}
	public int getMontantmaxencourspreter() {
		return montantmaxencourspreter;
	}
	public void setMontantmaxencourspreter(int montantmaxencourspreter) {
		this.montantmaxencourspreter = montantmaxencourspreter;
	}
	public int getMontantmaxencoursdonner() {
		return montantmaxencoursdonner;
	}
	public void setMontantmaxencoursdonner(int montantmaxencoursdonner) {
		this.montantmaxencoursdonner = montantmaxencoursdonner;
	}
	public int getSoldeminidemandeappelfond() {
		return soldeminidemandeappelfond;
	}
	public void setSoldeminidemandeappelfond(int soldeminidemandeappelfond) {
		this.soldeminidemandeappelfond = soldeminidemandeappelfond;
	}
	public int getSoldeminiremoubourserappelfond() {
		return soldeminiremoubourserappelfond;
	}
	public void setSoldeminiremoubourserappelfond(int soldeminiremoubourserappelfond) {
		this.soldeminiremoubourserappelfond = soldeminiremoubourserappelfond;
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	public LienCommunauteUser() {
		super();
	}
	public String getNomClasse() {
		return nomClasse;
	}
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	public void setCommunaute(Communaute communaute) {
		this.communaute = communaute;
	}
	

}
