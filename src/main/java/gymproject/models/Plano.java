package gymproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Entity
public class Plano {
    @Column(name = "nome_plano", length = 255)
    private String nomePlano;
    @Id
    private String codPlano;
    @Column(name = "duraçao_plano", nullable = false, length = 255)
    private String duracaoPlano;
    @Column(name = "valor_plano", nullable = false, length = 255)
    private String precoPlano;
    @Column(length = 255)
    private String integralPlano;
    @Column(length = 255)
    private String parcelamentoPlano;

}
