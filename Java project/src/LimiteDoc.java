import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//cette classe permet de limiter les champs de saisie en quelques propriet�s comme( la longeur(ou maximal) du champs-entier seulement ou non)
public class LimiteDoc extends PlainDocument{
	int maxCharacters;
	  boolean nbSeul = false;

	  public LimiteDoc(int maxChars)
	  {
	    maxCharacters = maxChars;
	  }

	  public LimiteDoc(int maxChars, boolean nb)
	  {
	    maxCharacters = maxChars;
	    nbSeul = nb;
	  }

	  public void insertString(int offs, String str, AttributeSet a)
	      throws BadLocationException
	  {
	    // Quitte la fonction si le champs est vide.
	    // Evite ainsi une erreur lors de l'initialisation du champ.
	    if(getLength() + str.length() == 0)
	      return;

	    // Rejette tout caract�re qui d�passerait la limite du champs
	    if ((getLength() + str.length()) <= maxCharacters)
	    {
	      // Si le champ accepte n'importe quel type de caract�res
	      if(!nbSeul)
	        super.insertString(offs, str, a);
	      // S'il ne doit accepter que les chiffres
	      else if(nbSeul && Character.isDigit(str.charAt(0)))
	        super.insertString(offs, str, a);
	      else // Beep si le caract�re n'est pas acceptable
	        Toolkit.getDefaultToolkit().beep();
	    }
	    // Beep si la limite de caract�res est atteinte
	    else
            JOptionPane.showMessageDialog(null, "CIN ne doit pas d�passer 7 charact�res", "erreur de champs", JOptionPane.WARNING_MESSAGE);

	  }
}
