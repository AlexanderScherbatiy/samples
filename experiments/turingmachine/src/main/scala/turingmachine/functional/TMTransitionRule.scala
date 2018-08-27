package turingmachine.functional

case class TMTransitionRuleInput(state: TMState, symbol: TMSymbol)

case class TMTransitionRuleOutput(state: TMState, symbol: TMSymbol, shift: TMStripShift)

case class TMTransitionRule(input: TMTransitionRuleInput, out: TMTransitionRuleOutput)