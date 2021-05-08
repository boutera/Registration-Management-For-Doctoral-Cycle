import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//cette classe permet de limiter les champs de saisie en quelques proprietés comme( la longeur(ou maximal) du champs-entier seulement ou non)
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

	    // Rejette tout caractère qui dépasserait la limite du champs
	    if ((getLength() + str.length()) <= maxCharacters)
	    {
	      // Si le champ accepte n'importe quel type de caractères
	      if(!nbSeul)
	        super.insertString(offs, str, a);
	      // S'il ne doit accepter que les chiffres
	      else if(nbSeul && Character.isDigit(str.charAt(0)))
	        super.insertString(offs, str, a);
	      else // Beep si le caractère n'est pas acceptable
	        Toolkit.getDefaultToolkit().beep();
	    }
	    // Beep si la limite de caractères est atteinte
	    else
            JOptionPane.showMessageDialog(null, "CIN ne doit pas dépasser 7 charactères", "erreur de champs", JOptionPane.WARNING_MESSAGE);

	  }
}
