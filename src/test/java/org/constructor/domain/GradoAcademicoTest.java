package org.constructor.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.constructor.web.rest.TestUtil;

public class GradoAcademicoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GradoAcademico.class);
        GradoAcademico gradoAcademico1 = new GradoAcademico();
        gradoAcademico1.setId(1L);
        GradoAcademico gradoAcademico2 = new GradoAcademico();
        gradoAcademico2.setId(gradoAcademico1.getId());
        assertThat(gradoAcademico1).isEqualTo(gradoAcademico2);
        gradoAcademico2.setId(2L);
        assertThat(gradoAcademico1).isNotEqualTo(gradoAcademico2);
        gradoAcademico1.setId(null);
        assertThat(gradoAcademico1).isNotEqualTo(gradoAcademico2);
    }
}
