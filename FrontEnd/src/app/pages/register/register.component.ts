import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  name = '';
  email = '';
  password = '';
  loading = false;
  error = '';
  success = '';

  submit(): void {
    this.error = '';
    this.success = '';
    if (!this.name || !this.email || !this.password) {
      this.error = 'Please fill all fields.';
      return;
    }
    this.loading = true;
    this.auth.register(this.name, this.email, this.password).subscribe({
      next: () => {
        this.success = 'Account created. Redirecting to login...';
        setTimeout(() => this.router.navigate(['/login']), 700);
      },
      error: err => {
        this.loading = false;
        this.error = err?.error?.message || 'Registration failed.';
      },
      complete: () => this.loading = false
    });
  }
}