package be.ucll.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Publication{


    protected Magazine(){}
    
    @NotBlank(message = "Editor is required.")
    private String editor;

    @NotBlank(message = "ISSN is required.")
    private String ISSN;




    public Magazine(String title, String editor, String ISSN, int publicationYear, int availableCopies) {
        super(title, publicationYear, availableCopies);
        setEditor(editor);
        setISSN(ISSN);
    }

    public void setEditor(@Valid String editor) {
        if (isInvalidString(editor)) {
            throw new DomainException("Editor is required.");
        }
        this.editor = editor;
    }
    
    public void setISSN(@Valid String ISSN) {
        if (isInvalidString(ISSN)) {
            throw new DomainException("ISSN is required.");
        }
        this.ISSN = ISSN;
    }

    public String getEditor() {
        return editor;
    }
    public String getISSN() {
        return ISSN;
    }

    public String toString() {
        return  "Magazine [title="+getTitle()+ ", author="+getEditor() +  ", ISBN="+getISSN()  + ", publicationYear="+getPublicationYear() + ", availableCopies=" + getAvailableCopies() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((editor == null) ? 0 : editor.hashCode());
        result = prime * result + ((ISSN == null) ? 0 : ISSN.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Magazine other = (Magazine) obj;
        if (editor == null) {
            if (other.editor != null)
                return false;
        } else if (!editor.equals(other.editor))
            return false;
        if (ISSN == null) {
            if (other.ISSN != null)
                return false;
        } else if (!ISSN.equals(other.ISSN))
            return false;
        return true;
    }

}

// Q&A Is the class in the correct folder?
// yes