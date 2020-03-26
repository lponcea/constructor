package org.constructor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.constructor.web.rest.TestUtil;

public class NumeroGradoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NumeroGrado.class);
        NumeroGrado numeroGrado1 = new NumeroGrado();
        numeroGrado1.setId(1L);
        NumeroGrado numeroGrado2 = new NumeroGrado();
        numeroGrado2.setId(numeroGrado1.getId());
        assertThat(numeroGrado1).isEqualTo(numeroGrado2);
        numeroGrado2.setId(2L);
        assertThat(numeroGrado1).isNotEqualTo(numeroGrado2);
        numeroGrado1.setId(null);
        assertThat(numeroGrado1).isNotEqualTo(numeroGrado2);
    }
}
