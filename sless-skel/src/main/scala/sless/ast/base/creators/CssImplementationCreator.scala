package sless.ast.base.creators

import sless.dsl.MixinDSL

class CssImplementationCreator extends PropertyCreator with SelectorCreator with ValueCreator with LintCreator with CommentCreator with MixinDSL with CompilableCreator {

}
