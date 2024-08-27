package be.ucll.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;


@Entity
@DiscriminatorValue("book")
public class Book extends Publication{
    
    @NotBlank(message = "Author is required.")
    private String author;

    @NotBlank(message = "ISBN is required.")
    private String ISBN;

    protected Book(){}



    public Book(String title, String author,String ISBN, int publicationYear, int availableCopies){
        super(title, publicationYear, availableCopies);
        setAuthor(author);
        setISBN(ISBN);
    }

    public String getAuthor(){
        return author;
    }
    public void setAuthor(String bookAuthor){
        if(isInvalidString(bookAuthor)){
            throw new DomainException("Author is required.");
        }
         this.author = bookAuthor;
    }

    public String getISBN(){
        return this.ISBN;
    }
    public void setISBN(String bookISBN){
        if(isInvalidString(bookISBN)){
            throw new DomainException("ISBN is required.");
        }
        if(bookISBN.replace("-", "").length() != 13){
            throw new DomainException("ISBN should be 13 digits long.");
        }
         this.ISBN = bookISBN;  
    }

    public String toString() {
        return  "Book [title="+getTitle()+ ", author="+getAuthor() +  ", ISBN="+getISBN()  + ", publicationYear="+getPublicationYear() + ", availableCopies=" + getAvailableCopies() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
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
        Book other = (Book) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (ISBN == null) {
            if (other.ISBN != null)
                return false;
        } else if (!ISBN.equals(other.ISBN))
            return false;
        return true;
    }

} // Q&A Should the errors be exactly the same as in the story pdfs? If we add a dot at the end of a sentence, will that result in a fail mark? Copy it.

// Code review:
//    - Which convention for names of method parameters do we use?
//    - publicationYear must be publicationYear
//    - Do we write get() and immediately after it the set() or first all get() methods and then all set()?
//    - Which way for getting the year will we use? I used a bit different one.
//    - this.ISBN can be just ISBN