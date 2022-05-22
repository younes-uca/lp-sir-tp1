import {Injectable} from '@angular/core';
import {Commande} from '../model/commande.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommandeService {

  private _commande: Commande;
  private _commandes: Array<Commande>;

  constructor(private http: HttpClient) {
  }

  public save(): Observable<Commande> {
    return this.http.post<Commande>('http://localhost:8036/api/admin/commande/', this.commande);
  }
  public findAll() {
    return this.http.get<Array<Commande>>('http://localhost:8036/api/admin/commande/');
  }

  get commande(): Commande {
    if (this._commande == null) {
      this._commande = new Commande();
    }
    return this._commande;
  }

  set commande(value: Commande) {
    this._commande = value;
  }

  get commandes(): Array<Commande> {
    if (this._commandes == null) {
      this._commandes = new Array<Commande>();
    }
    return this._commandes;
  }

  set commandes(value: Array<Commande>) {
    this._commandes = value;
  }
}
