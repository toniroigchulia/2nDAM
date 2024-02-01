from odoo import models, fields
from odoo.exceptions import ValidationError

class Book(models.Model):
    _name = "library.book"
    _description = "Book"
    
    name = fields.Char("Título", required=True)
    isbn = fields.Char("ISBN")
    active = fields.Boolean("Activo?", default=True)
    date_published = fields.Date()
    image = fields.Binary("Cubierta")
    publisher_id = fields.Many2one("res.partner", string="Editorial")
    author_ids = fields.Many2many("res.partner", string="Autores")
    
    def _check_isbn(self):
        self.ensure_one()
        digits = [int(x) for x in self.isb if x.isdigit()]
        if len(digits) == 13:
            ponderations = [1,3]*6
            terms=[a*b for a, b in zip(digits[:12], ponderations)]
            remain = sum(terms)%10
            check= 10 - remain if remain !=0 else 0
            return digits[-1] == check
            
    def button_check_isbn(self):
        for book in self:
            if not book.isbn:
                raise ValidationError("Por favor, proporciona un ISBN para %s" %book.isbn)
            if book.isbn and not book._check_isbn():
                raise ValidationError("%s ISBN no es valido" %book.isbn)
        return True