package GHEBACKEND.GHEBACKEND.model.DonneesReferentielles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Table(name = "T_DOCUMENT")
public class Documents {

    @Id
    @Column(name = "DOC_CODE")
    private Integer doc_code;

    @Column(name = "DOC_TYPE")
    private String doc_type;

    @Column(name = "DOC_NAME")
    private String doc_name;

    @Column(name = "DOC_FILE")
    private String doc_file;

    // @Column(name = "TDC_CODE")
    // private Integer tdc_code;
    
    @ManyToOne
    @JoinColumn(name = "tdc_code", referencedColumnName = "tdc_code")
    private TypeDocumentModel typeDocumentModel;

    

}
