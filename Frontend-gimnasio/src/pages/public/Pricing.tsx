import React from 'react';
import { Header } from '../../components/layout/Header';
import { Footer } from '../../components/layout/Footer';
import { Card } from '../../components/common/Card';
import { Button } from '../../components/common/Button';

export const Pricing: React.FC = () => {
  const plans = [
    {
      name: 'Básico',
      price: 29,
      features: ['10 clases/mes', 'Acceso a clases grupales', 'App móvil'],
    },
    {
      name: 'Premium',
      price: 49,
      features: ['Clases ilimitadas', 'Entrenador personal', 'Acceso 24/7', 'Nutrición'],
    },
    {
      name: 'VIP',
      price: 79,
      features: [
        'Clases ilimitadas',
        'Entrenador personal dedicado',
        'Acceso 24/7',
        'Plan nutricional personalizado',
        'Consultas ilimitadas',
      ],
    },
  ];

  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      <main className="flex-1 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <h1 className="text-4xl font-bold text-center mb-12">Planes y Precios</h1>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          {plans.map((plan) => (
            <Card key={plan.name} title={plan.name} className="text-center">
              <div className="mb-6">
                <span className="text-4xl font-bold">€{plan.price}</span>
                <span className="text-gray-500">/mes</span>
              </div>
              <ul className="space-y-2 mb-6">
                {plan.features.map((feature, index) => (
                  <li key={index} className="text-gray-600">
                    ✓ {feature}
                  </li>
                ))}
              </ul>
              <Button variant="primary" fullWidth>
                Elegir Plan
              </Button>
            </Card>
          ))}
        </div>
      </main>
      <Footer />
    </div>
  );
};

