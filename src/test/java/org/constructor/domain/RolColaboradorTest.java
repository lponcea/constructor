package org.constructor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.constructor.web.rest.TestUtil;

public class RolColaboradorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RolColaborador.class);
        RolColaborador rolColaborador1 = new RolColaborador();
        rolColaborador1.setId(1L);
        RolColaborador rolColaborador2 = new RolColaborador();
        rolColaborador2.setId(rolColaborador1.getId());
        assertThat(rolColaborador1).isEqualTo(rolColaborador2);
        rolColaborador2.setId(2L);
        assertThat(rolColaborador1).isNotEqualTo(rolColaborador2);
        rolColaborador1.setId(null);
        assertThat(rolColaborador1).isNotEqualTo(rolColaborador2);
    }
}
