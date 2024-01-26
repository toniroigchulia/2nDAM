from odoo import models, fields

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