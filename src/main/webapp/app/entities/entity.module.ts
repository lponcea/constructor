import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'asignatura',
        loadChildren: () => import('./asignatura/asignatura.module').then(m => m.ConstructorAsignaturaModule)
      },
      {
        path: 'numero-grado',
        loadChildren: () => import('./numero-grado/numero-grado.module').then(m => m.ConstructorNumeroGradoModule)
      },
      {
        path: 'grado-academico',
        loadChildren: () => import('./grado-academico/grado-academico.module').then(m => m.ConstructorGradoAcademicoModule)
      },
      {
        path: 'curso',
        loadChildren: () => import('./curso/curso.module').then(m => m.ConstructorCursoModule)
      },
      {
        path: 'ficha',
        loadChildren: () => import('./ficha/ficha.module').then(m => m.ConstructorFichaModule)
      },
      {
        path: 'colaborador',
        loadChildren: () => import('./colaborador/colaborador.module').then(m => m.ConstructorColaboradorModule)
      },
      {
        path: 'editorial',
        loadChildren: () => import('./editorial/editorial.module').then(m => m.ConstructorEditorialModule)
      },
      {
        path: 'rol-colaborador',
        loadChildren: () => import('./rol-colaborador/rol-colaborador.module').then(m => m.ConstructorRolColaboradorModule)
      },
      {
        path: 'categoria',
        loadChildren: () => import('./categoria/categoria.module').then(m => m.ConstructorCategoriaModule)
      },
      {
        path: 'modalidad',
        loadChildren: () => import('./modalidad/modalidad.module').then(m => m.ConstructorModalidadModule)
      },
      {
        path: 'version',
        loadChildren: () => import('./version/version.module').then(m => m.ConstructorVersionModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ConstructorEntityModule {}
