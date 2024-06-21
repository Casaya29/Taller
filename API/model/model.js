const mongoose = require('mongoose');

const dataSchema = new mongoose.Schema(
{
    apellido: {
        required: true,
        type: String
    },
    ubicacion: {
        required: true,
        type: String
    },
    vivienda: {
        required: true,
        type: String
    },
    riesgo: {
        required: true,
        type: String
    },
}
);

module.exports = mongoose.model('Familia', dataSchema);
