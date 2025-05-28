import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { useState } from 'react';
import './App.css';

interface DietType {
  id: string;
  name: string;
  description: string;
}

const dietTypes: DietType[] = [
  {
    id: 'mediterranea',
    name: 'Mediterrânea',
    description: 'Dieta rica em azeite de oliva, peixes, grãos integrais, legumes e frutas. Foco em saúde cardiovascular.'
  },
  {
    id: 'low-carb',
    name: 'Low Carb',
    description: 'Dieta com redução de carboidratos, aumento de proteínas e gorduras boas. Foco em emagrecimento.'
  },
  {
    id: 'cetogenica',
    name: 'Cetogênica',
    description: 'Dieta com ingestão muito baixa de carboidratos e alta em gorduras. Para perda de gordura rápida.'
  },
  {
    id: 'vegetariana',
    name: 'Vegetariana',
    description: 'Dieta sem carnes, incluindo ovos, laticínios, grãos, vegetais e leguminosas.'
  }
];

function Home() {
  return (
    <div className="home">
      <section className="hero">
        <h1>Bem-vindo ao NutriFácil</h1>
        <p>Sua jornada para uma vida mais saudável começa aqui</p>
      </section>
      
      <section className="diet-types">
        <h2>Nossos Planos Alimentares</h2>
        <div className="diet-grid">
          {dietTypes.map((diet) => (
            <div key={diet.id} className="diet-card">
              <h3>{diet.name}</h3>
              <p>{diet.description}</p>
              <Link to={`/dieta/${diet.id}`} className="button">
                Saiba mais
              </Link>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
}

function DietDetail({ id }: { id: string }) {
  const diet = dietTypes.find(d => d.id === id);
  
  if (!diet) {
    return <div>Dieta não encontrada</div>;
  }

  return (
    <div className="diet-detail">
      <h1>{diet.name}</h1>
      <p>{diet.description}</p>
      <Link to="/" className="button">Voltar</Link>
    </div>
  );
}

function App() {
  return (
    <Router>
      <div className="app">
        <nav className="navbar">
          <Link to="/" className="logo">NutriFácil</Link>
          <div className="nav-links">
            <Link to="/">Home</Link>
            <Link to="/sobre">Sobre</Link>
            <Link to="/contato">Contato</Link>
          </div>
        </nav>

        <main>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/dieta/:id" element={<DietDetail id={window.location.pathname.split('/').pop() || ''} />} />
          </Routes>
        </main>

        <footer>
          <p>&copy; 2024 NutriFácil. Todos os direitos reservados.</p>
        </footer>
      </div>
    </Router>
  );
}

export default App; 