import java.time.Duration;
import java.time.LocalTime;

public class Lavatrice {

    private int capacità;
    private int livelloAcqua;
    private int temperatura;
    private int velocitàRotazione;
    private boolean detersivo;
    private int stato;
    private float credito;
    private LocalTime dataOraAvvio;

    public Lavatrice (int capacità){
        if (capacità!=8 && capacità!=10 && capacità!=14){
            System.err.println("Capacità non valida:IMPOSTATA CAPACITà PREDEFINITA 8KG");
            this.capacità=8;
        } else
        this.capacità = capacità;

    }

private void inizializza() {
    // inizializzazione attributi
    livelloAcqua=0;
    temperatura=0;
    velocitàRotazione=0;
    stato=0;
    credito=0;
    detersivo=false;
}

public void agiumgiAcqua(int litri){
    if (litri<=0)
            System.err.println("Quantità aqua non valida");
    else if (livelloAcqua+litri>6)
            System.err.println("Quantità di acqua superiore al livello massimo");
    else 
            livelloAcqua+=litri;

    }
public void setTemperatura(int gradi) {
		if (gradi==40 || gradi==60 || gradi==90)
			this.temperatura = gradi;
		else
			System.out.println("Temperatura non valida");		
	}
	
	public void setVelocitàRotazione(int velocitàRotazione) {
		if (velocitàRotazione==600 || velocitàRotazione==800 || velocitàRotazione==1000)
			this.velocitàRotazione = velocitàRotazione;
		else
			System.out.println("Velocità di rotazione non valida");	
	}
	
	public void aggiungiDetersivo() {
		detersivo=true;
	}
	
	public void aggiungiMoneta(float moneta) {
		if (moneta==0.10 || moneta==0.20 || moneta==0.50 || moneta==1.0 || moneta==2.0)
			credito+=moneta;		//credito=credito+moneta
		else
			System.out.println("Moneta non valida");
	}
	
	public float getCosto() {		
		switch(capacità) {
			case 8:
				return 4.50f;			
			case 10:
				return 5.50f;			
			case 14:
				return 6.50f;
			default:
				return 0;		//mai vero		
		}
	}
	
	public void start() {
		if (credito<getCosto())
			System.out.println("Credito insufficiente");
		else if (livelloAcqua<4)
			System.out.println("Livello acqua insufficiente");
		else if (temperatura==0)
			System.out.println("Temperatura non impostata");
		else if (velocitàRotazione==0)
			System.out.println("Velocità di rotazione non impostata");
		else if (!detersivo)
			System.out.println("Detersivo non aggiunto");
		else if (stato!=0) 
			System.out.println("La lavatrice non è libera");
		else {				//ho verificato tutto=>avvio il lavaggio
			stato=1;		//lavaggio in corso
			dataOraAvvio=LocalTime.now();	//memorizzo la data/ora avvio
		}
	}	
	
	
	public void stop() {
		if (stato==1)
			stato=2;		//lavaggio terminato
		else
			System.out.println("Lavatrice già ferma");
	}
	
    public String getStato() {
        LocalTime lt = LocalTime.now();  // Data/ora corrente

        if (stato == 1 && Duration.between(dataOraAvvio, lt).getSeconds() > 60)
            stato = 2;  // Lavaggio terminato

        switch (stato) {
            case 0:
                return "Libera";
            case 1:
                return "Lavaggio in corso. Tempo trascorso: " +
                        Duration.between(dataOraAvvio, lt).getSeconds() + " secondi";
            case 2:
                return "Lavaggio terminato";
            default:
                return "";
        }
    }
	}

	public int getCapacità() {
		return capacità;
	}

	public int getLivelloAcqua() {
		return livelloAcqua;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public int getVelocitàRotazione() {
		return velocitàRotazione;
	}

	public boolean detersivoAggiunto() {
		return detersivo;
	}

	public float getCredito() {
		return credito;
	}
		
	public void svuota() {
		if (stato==2) 
			inizializza();		
			
		else if (stato==0)
			System.out.println("Lavatrice già vuota");
		else
			System.out.println("Lavaggio in corso");
			
	}
		
	
}