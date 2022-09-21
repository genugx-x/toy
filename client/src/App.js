import './App.css';
import State from "./part2/State";

function App() {
  return (
      <div>
          <h1>Start React 200!</h1>
          <p>CSS 적용하기</p>
          {/*<LifecycleEx prop_value = 'FromApp.js' />*/}
          <State>
              <span> node from App.js</span>
          </State>
      </div>
  );
}

export default App;
