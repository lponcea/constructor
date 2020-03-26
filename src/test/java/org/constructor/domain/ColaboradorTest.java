package org.constructor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.constructor.web.rest.TestUtil;

public class ColaboradorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Colaborador.class);
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setId(1L);
        Colaborador colaborador2 = new Colaborador();
        colaborador2.setId(colaborador1.getId());
        assertThat(colaborador1).isEqualTo(colaborador2);
        colaborador2.setId(2L);
        assertThat(colaborador1).isNotEqualTo(colaborador2);
        colaborador1.setId(null);
        assertThat(colaborador1).isNotEqualTo(colaborador2);
    }
}
