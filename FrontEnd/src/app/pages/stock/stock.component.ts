import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../core/api.service';
import { Product, StockTransaction } from '../../core/models';

@Component({
  selector: 'app-stock',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './stock.component.html'
})
export class StockComponent {
  private readonly api = inject(ApiService);
  products: Product[] = [];
  transactions: StockTransaction[] = [];
  productId: number | null = null;
  type: 'STOCK_IN' | 'STOCK_OUT' = 'STOCK_IN';
  quantity = 1;
  description = '';
  error = '';
  success = '';

  ngOnInit(): void {
    this.api.products().subscribe(p => this.products = p);
    this.loadTransactions();
  }

  loadTransactions(): void {
    this.api.stockTransactions().subscribe({
      next: t => this.transactions = t,
      error: () => this.error = 'Unable to load stock transactions.'
    });
  }

  submit(): void {
    this.error = '';
    this.success = '';
    if (!this.productId || this.quantity <= 0) {
      this.error = 'Select a product and enter a valid quantity.';
      return;
    }
    this.api.addStock(this.productId, this.type, this.quantity, this.description).subscribe({
      next: () => {
        this.success = `${this.type === 'STOCK_IN' ? 'Stock added' : 'Stock removed'} successfully.`;
        this.quantity = 1; this.description = '';
        this.api.products().subscribe(p => this.products = p);
        this.loadTransactions();
      },
      error: err => this.error = err?.error?.message || 'Stock operation failed.'
    });
  }
}