import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../core/api.service';
import { DashboardSummary, Product } from '../../core/models';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent {
  private readonly api = inject(ApiService);
  summary: DashboardSummary | null = null;
  lowStock: Product[] = [];
  error = '';

  ngOnInit(): void {
    this.api.dashboard().subscribe({
      next: data => this.summary = data,
      error: () => this.error = 'Unable to load dashboard.'
    });
    this.api.lowStock().subscribe({
      next: data => this.lowStock = data
    });
  }
}