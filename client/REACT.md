리액트 시작하기
----------------

1. node.js 설치
1. node.js 및 npm 설치 확인
    > $ node -v  
      $ npm -v
1. create-react-app 설치
    > $ npm install -g create-react-app  
      $ create-react-app **./프로젝트명**

1. react 서버 실행
    > $ cd **./프로젝트명**  
      $ npm start  
      
1. *http://localhost:3000 접속*
-------------

리액트 Component 사용하기
----
1. 


---

Part 1
---
> 1. render()
> 1. constructor()
> 1. getDerivedStateFormProps()
> 1. componentDidMount()
> 2. class
> 3. arrow
> 4. forEach
> 5. Map
---

Part 2

---
> 1. props : 상위 컴포넌트에서 하위 컴포넌트로 데이터를 전달할 때 사용 
> 2. state : 컴포넌트 내부에서 전역 변수처럼 사용
---

``` javascript
import {Component} from 'react';
import dataType from 'prop-types';

class Props extend 'Component' {
   render() {
      return(
         ···
      )
   }
}

// props 기본값 설정
Props.defaultProps = {
    String: "react",
    Number: 123
}

// props 속성 설정
Props.propTypes = {
    String: dataType.string,
    Number: dataType.number,
    Boolean: dataType.bool,
    Array: dataType.array,
    ObjectJson: dataType.shape({ // object 멤버 속성 정의
        name: dataType.string.isRequired, // 획득 요구 
        age: dataType.number
        gender: dataType.bool.isRequired
    }),
    Function: dataType.func,
}
```
---
> 1. props와 state의 변경에 따라 render() 함수를 호출
> 2. Component : 비교 대상이 완전히 동일하지 않으면 변경이 발생했다고 보고 render() 함수 호출
> 3. PureComponent
