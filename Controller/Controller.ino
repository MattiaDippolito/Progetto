#define Joystick_Y A0
#define Joystick_X A1
#define Joystick_Click 2
#define Led-White 3
#define Led-Green 4
#define Led-Red 5

void setup() {
  Serial.begin(9600);
  
  pinMode(A0, INPUT);
  pinMode(A1, INPUT);
  pinMode(2, INPUT);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);

  digitalWrite(3, LOW);
  digitalWrite(4, LOW);
  digitalWrite(5, LOW);


//Serie di accensioni di led che indicano il collegamento del joystick al PC
  LampeggioLed(400, "Bianco", 3);
  AccendiLed("Bianco");
  AccendiLed("Verde");
  delay(1000);
  SpegniLed("Bianco");
  SpegniLed("Verde");
  
}

void loop() 
{
  if(Serial.available()>0)
  {
    char carattere=Serial.read();

//Se Legge 0 dalla seriale si gioca
    if(carattere=='0')
    {
      //Il Led Bianco indica Che si sta giocando la partita
      AccendiLed("Bianco");
      
      VelocitaFissa();
    }

//Se Legge 3 dalla seriale, il led verde inizerà a lampeggiare, indicando che si è fatto un punto a favore
    if(carattere=='1')
    {
      LampeggioLed(1000, "Verde", 1);
    }

//Se Legge 2 dalla seriale, il led rosso inizerà a lampeggiare, indicando che si è subito un punto a sfavore
    if(carattere=='2')
    {
      LampeggioLed(1000, "Rosso", 1);
    }

//Se Legge 3 dalla seriale si spegne il led bianco ed il gioco termina
    if(carattere=='3')
    {
      SpegniLed("Bianco");
    }

//Se Legge 3 dalla seriale si spegne il led bianco ed il gioco termina
    if(carattere=='4')
    {
      AccendiLed("Verde");
      delay(5000);
      SpegniLed("Verde");
    }

//Se Legge 3 dalla seriale si spegne il led bianco ed il gioco termina
    if(carattere=='5')
    {
      AccendiLed("Rosso");
      delay(5000);
      SpegniLed("Rosso");
    }
  }
}

//----------------------------------------------------------------------------------------
//Se vuoi che si muovi a velocità variabile usa questa funzione
//

void VariaVelocita()
{
  int x = analogRead(Joystick_X);
  int y = analogRead(Joystick_Y);
  int mappaX= map(x,0,1024,1,5);
  int mappaY= map(x,0,1024,1,5);
  if(x<508)
    Serial.print('s' + String(mappaX)+';');
  else if(x>508)
    Serial.print('w' + String(mappaX)+';');
}

//----------------------------------------------------------------------------------------
//Se vuoi che si muovi a velocità fissa usa questa funzione
//
void VelocitaFissa()
{
  int x = analogRead(Joystick_X);
  int y = analogRead(Joystick_Y);
  if(x<509)
    Serial.print('s');
  else if(x>509)
    Serial.print('w');
  else if(x==509)
    Serial.print('c');
}

//----------------------------------------------------------------------------------------
//Accendi il led
//
void AccendiLed(String colore)
{
  if(colore=="Bianco")
  {
    digitalWrite(3, HIGH);
  }
  if(colore=="Verde")
  {
    digitalWrite(4, HIGH);
  }
  if(colore=="Rosso")
  {
    digitalWrite(5, HIGH);
  }
}

//----------------------------------------------------------------------------------------
//Accendi il led
//
void SpegniLed(String colore)
{
  if(colore=="Bianco")
  {
    digitalWrite(3, LOW);
  }
  if(colore=="Verde")
  {
    digitalWrite(4, LOW);
  }
  if(colore=="Rosso")
  {
    digitalWrite(5, LOW);
  }
}

//----------------------------------------------------------------------------------------
//Lampeggia
//
void LampeggioLed(int tempo, String colore, int cicli)
{
  if(colore=="Bianco")
  {
    for(int i=0; i<cicli; i++)
    {
      digitalWrite(3, HIGH);
      delay(tempo);
      digitalWrite(3, LOW);
      delay(tempo);
    }
  }
  
  if(colore=="Verde")
  {
    for(int i=0; i<cicli; i++)
    {
      digitalWrite(4, HIGH);
      delay(tempo);
      digitalWrite(4, LOW);
      delay(tempo);
    }
  }
  
  if(colore=="Rosso")
  {
    for(int i=0; i<cicli; i++)
    {
      digitalWrite(5, HIGH);
      delay(tempo);
      digitalWrite(5, LOW);
      delay(tempo);
    }
  }
}