import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ConstructorSharedModule } from 'app/shared/shared.module';
import { UserManagementComponent } from './user-management.component';
import { UserManagementDetailComponent } from './user-management-detail.component';
import { UserManagementUpdateComponent } from './user-management-update.component';
import { UserManagementDeleteDialogComponent } from './user-management-delete-dialog.component';
import { userManagementRoute } from './user-management.route';
import { PasswordStrengthBarModule } from './../../account/password/password-strength-bar.module';

@NgModule({
  imports: [ConstructorSharedModule, RouterModule.forChild(userManagementRoute), PasswordStrengthBarModule],
  declarations: [
    UserManagementComponent,
    UserManagementDetailComponent,
    UserManagementUpdateComponent,
    UserManagementDeleteDialogComponent
  ],
  entryComponents: [UserManagementDeleteDialogComponent]
})
export class UserManagementModule {}
