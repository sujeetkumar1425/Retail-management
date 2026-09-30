import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';
import { Category, Product } from '../../core/models';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './products.component.html'
})
export class ProductsComponent {
  private readonly api = inject(ApiService);
  products: Product[] = [];
  categories: Category[] = [];
  showForm = false;
  editingId: number | null = null;
  error = '';
  form: Product = { name: '', sku: '', price: 0, quantity: 0, reorderLevel: 10, category: null };

  ngOnInit(): void { this.load(); this.api.categories().subscribe(c => this.categories = c); }

  load(): void {
    this.api.products().subscribe({
      next: p => this.products = p,
      error: () => this.error = 'Unable to load products.'
    });
  }

  openNew(): void {
    this.editingId = null;
    this.form = { name: '', sku: '', price: 0, quantity: 0, reorderLevel: 10, category: this.categories[0]?.id ? {id: this.categories[0].id!} : null };
    this.showForm = true;
  }

  edit(p: Product): void {
    this.editingId = p.id!;
    this.form = { ...p, category: p.category ? { ...p.category } : null };
    this.showForm = true;
  }

  save(): void {
    this.error = '';
    const request = this.editingId
      ? this.api.updateProduct(this.editingId, this.form)
      : this.api.createProduct(this.form);
    request.subscribe({
      next: () => { this.showForm = false; this.load(); },
      error: err => this.error = err?.error?.message || 'Unable to save product.'
    });
  }

  remove(id: number): void {
    if (!confirm('Delete this product?')) return;
    this.api.deleteProduct(id).subscribe({ next: () => this.load(), error: () => this.error = 'Delete failed.' });
  }
}