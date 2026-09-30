import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';
import { Category } from '../../core/models';

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './categories.component.html'
})
export class CategoriesComponent {
  private readonly api = inject(ApiService);
  categories: Category[] = [];
  name = '';
  description = '';
  error = '';

  ngOnInit(): void { this.load(); }

  load(): void {
    this.api.categories().subscribe({ next: c => this.categories = c, error: () => this.error = 'Unable to load categories.' });
  }

  add(): void {
    if (!this.name.trim()) { this.error = 'Category name is required.'; return; }
    this.api.createCategory({ name: this.name.trim(), description: this.description.trim() }).subscribe({
      next: () => { this.name = ''; this.description = ''; this.error = ''; this.load(); },
      error: err => this.error = err?.error?.message || 'Unable to create category.'
    });
  }

  remove(id: number): void {
    if (!confirm('Delete this category?')) return;
    this.api.deleteCategory(id).subscribe({ next: () => this.load(), error: () => this.error = 'Delete failed.' });
  }
}